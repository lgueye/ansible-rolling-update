# ansible-rolling-update
ansible-playbook -i /etc/ansible/hosts -e "app_rev1=8e4accb app_rev2=7c76359" roll-update.yml
