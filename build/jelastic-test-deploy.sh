#!/bin/bash

jelastic_api_url='app.jpc.infomaniak.com'

project_id='boutique-service-dev'
env_name='maven'
node_id='4280'



login() {

    echo "=============================== Login to provider ==============================="
    
    SESSION=$(curl -d "login=$JELASTIC_USER&password=$JELASTIC_PASSWORD" -H "Content-Type: application/x-www-form-urlencoded" -X POST "https://$jelastic_api_url/1.0/users/authentication/rest/signin" | \
        sed -E 's/^.*"session":"([^"]+)".*$/\1/')
    [ -n "$SESSION" ] || {
        echo "Failed to login with credentials supplied"
        exit 0
    }
    
    echo "Login ok, session:$SESSION"
    
	
	echo "=============================== Login end ==============================="
}

deploy_dev() {
    echo "=============================== DEPLOY TO STAGE $env_name | $(date +%d.%m.%y_%H-%M-%S) ==============================="

		echo "Deploy to provider:$jelastic_api_url, with env:$env_name, projectId:$project_id, nodeId:$node_id"
        
        curl -s "https://$jelastic_api_url/1.0/environment/deployment/rest/builddeployproject?delay=1&envName=$env_name&session=$SESSION&nodeid=$node_id&projectid=$project_id&isSequential=false"
		
		echo "Deploy command send"

    echo "=============================== DEPLOY END $env_name | $(date +%d.%m.%y_%H-%M-%S) ==============================="
}


login
create_environment 