#!/bin/bash

JELASTIC_URL='app.jpc.infomaniak.com'
PROJECT_ID='boutique-service-stage'
ENV_NAME='maven'
NODE_ID='4280'
BOUTIQUE_URL='boutique-service-stage.jcloud.ik-server.com'



login() {

	echo "=============================== Login to provider ==============================="
    echo "$JELASTIC_USER"
    echo "$JELASTIC_PASSWORD"
    
    # récupération de la valeur session dans la réponse
    SESSION=$(curl -d "login=$JELASTIC_USER&password=$JELASTIC_PASSWORD" -H "Content-Type: application/x-www-form-urlencoded" -X POST "https://$JELASTIC_URL/1.0/users/authentication/rest/signin" | \
        sed -E 's/^.*"session":"([^"]+)".*$/\1/')
    
   
    # test de la validite de la session
    # pas vide, unqiquement caractères alpha et num, taille 36
    if [[ -n $SESSION && $SESSION =~ ^[0-9a-z]+$ && ${#SESSION} -eq 36 ]]
	then
	    echo "Login ok, session:$SESSION"
	else
	    echo "Failed to login with credentials supplied"
        exit 0
	fi
}

deploy_stage() {
    
    echo "=============================== DEPLOY TO STAGE $ENV_NAME | $(date +%d.%m.%y_%H-%M-%S) ==============================="

		# affichage du dernier commit
		echo "Last commit id: $TRAVIS_COMMIT, message: $TRAVIS_COMMIT_MESSAGE"
		
		# log de déploiement
		echo "Deploy to provider:$JELASTIC_URL, with env:$ENV_NAME, projectId:$PROJECT_ID, nodeId:$NODE_ID"
        
        # appel de l'api pour lancer le build et le deploy du projet
        DEPLOY_RESPONSE=$(curl -s "https://$JELASTIC_URL/1.0/environment/deployment/rest/builddeployproject?delay=1&envName=$ENV_NAME&session=$SESSION&nodeid=$NODE_ID&projectid=$PROJECT_ID&isSequential=false" | \ 
		 jq --raw-output '.result')
		
		if [DEPLOY_RESPONSE -eq 0]
		then
			echo "Deploy command successfully send"
		else
			exit 1
		fi
}

wait_about_env() {
	echo "=============================== WAITING ABOUT ENV $ENV_NAME | $(date +%d.%m.%y_%H-%M-%S) ==============================="
	
	echo "sleeping 10 second about env is up" 
	
	# attente de 10s sur le déploiement
	sleep 10
	
	# log
	echo "10s sleep end"
	
	# tant que les comit id's ne matche pas on check avec 5 secondes d'attente
	until check_commit_id_coherence -eq 1 ; do
    	sleep 5
        echo "check commit id coherence..."
    done
	
}

check_commit_id_coherence(){
	
	# récupération du commit id de l'api actuellemtn déployé
	COMMIT_ID=$(curl "http://$BOUTIQUE_URL/boutique/build-info" | \
       jq --raw-output '."git.commit.id"')
    
    # log du dernier commit avec le dernier commit déployée    
    echo "Commit id from API:$COMMIT_ID, commit id expected:$TRAVIS_COMMIT"    
    
   
    # comparaison des commit id
    if [ "$COMMIT_ID" == "$TRAVIS_COMMIT" ] 
    then
      echo "comit id's match"
      return 0
    else
      echo "comit id's dont match"
      return 1
    fi
}

login
deploy_stage
wait_about_env



	
