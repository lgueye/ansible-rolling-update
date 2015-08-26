# ansible-rolling-update
ansible-playbook -i /etc/ansible/hosts -e "app_rev1=f5dd105 app_rev2=724d202" roll-update.yml
