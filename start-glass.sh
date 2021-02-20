#!/bin/bash

set -e

deploy_dir=/opt/app/deploy
domains_dir=/opt/glassfish5/glassfish/domains

export domains_dir

if [ ! "$(ls -A ${domains_dir})" ]; then
    asadmin create-domain --nopassword domain1
fi

if [ "$(ls -A ${deploy_dir})" ]; then
    cp ${deploy_dir}/* ${domains_dir}/domain1/autodeploy
fi

DEBUG_MODE=${DEBUG:-false}
exec asadmin start-domain --debug=${DEBUG_MODE} --watchdog

