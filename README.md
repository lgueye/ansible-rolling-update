# ansible-rolling-update
ansible-playbook -i /etc/ansible/hosts -e "app_rev1=8e4accb app_rev2=54783ce" roll-update.yml
