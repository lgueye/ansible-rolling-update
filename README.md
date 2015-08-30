# ansible-rolling-update
ansible-playbook -i /etc/ansible/hosts -e "app_rev1=72929fb app_rev2=17d0086" roll-update.yml
