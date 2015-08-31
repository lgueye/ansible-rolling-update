# ansible-rolling-update
ansible-playbook -i /etc/ansible/hosts -e "app_rev1=60df38b app_rev2=60df38b" roll-update.yml
