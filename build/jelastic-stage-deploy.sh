#!/bin/bash

jelastic_api_url='app.jpc.infomaniak.com'
project_id='boutique-service-stage'
env_name='maven'
node_id='4280'
boutique_url='boutique-service-stage.jcloud.ik-server.com'



login() {

	echo "=============================== Login to provider ==============================="
    echo "$JELASTIC_USER"
    echo "$JELASTIC_PASSWORD"
    
    SESSION=$(curl -d "login=$JELASTIC_USER&password=$JELASTIC_PASSWORD" -H "Content-Type: application/x-www-form-urlencoded" -X POST "https://$jelastic_api_url/1.0/users/authentication/rest/signin" | \
        sed -E 's/^.*"session":"([^"]+)".*$/\1/')
    [ -n "$SESSION" ] || {
        echo "Failed to login with credentials supplied"
        exit 0
    }
    
    echo "Login ok, session:$SESSION"
    
	
	echo "=============================== Login end ==============================="
}

deploy_stage() {
    
    echo "=============================== DEPLOY TO STAGE $env_name | $(date +%d.%m.%y_%H-%M-%S) ==============================="

		echo "LasCommit: $TRAVIS_COMMIT, message: $TRAVIS_COMMIT_MESSAGE"
		
		echo "Deploy to provider:$jelastic_api_url, with env:$env_name, projectId:$project_id, nodeId:$node_id"
        
        curl -s "https://$jelastic_api_url/1.0/environment/deployment/rest/builddeployproject?delay=1&envName=$env_name&session=$SESSION&nodeid=$node_id&projectid=$project_id&isSequential=false" 
		
		echo "Deploy command send"

    echo "=============================== DEPLOY END $env_name | $(date +%d.%m.%y_%H-%M-%S) ==============================="
}

wait_about_env() {
	echo "=============================== WAITING ABOUT ENV $env_name | $(date +%d.%m.%y_%H-%M-%S) ==============================="
	
	echo "sleeping 10 second to deploy" 
	
	sleep 10
	
	echo "sleep end"
	
	echo "=============================== WAITING ABOUT ENV END $env_name | $(date +%d.%m.%y_%H-%M-%S) ==============================="
	
}

check_commit_id_coherence(){
	COMMIT_ID=$(curl "http://$boutique_url/boutique/build-info" | \
       jq --raw-output '."git.commit.id"')
        
    echo "Commit id from API:$COMMIT_ID, commiId expected:$TRAVIS_COMMIT"    
    if [ "$COMMIT_ID" == "$TRAVIS_COMMIT" ] 
    then
      echo "match"
      return 1
    else
      echo "dont match"
      return 0
    fi
}

login
deploy_stage
wait_about_env

 while [  !check_commit_id_coherence ]; do
             sleep 5
             echo "sleeping 5"
         done

	
