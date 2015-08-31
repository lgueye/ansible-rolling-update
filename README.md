# ansible-rolling-update
ansible-playbook -i /etc/ansible/hosts -e "app_rev1=80f52eb app_rev2=80f52eb" roll-update.yml
