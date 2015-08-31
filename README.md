# ansible-rolling-update
ansible-playbook -i /etc/ansible/hosts -e "app_rev1=8e4accb app_rev2=5aaa19d" roll-update.yml
