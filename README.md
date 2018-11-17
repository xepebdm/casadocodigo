# casadocodigo
Projeto de Formação Desenvolvedor Java com SpringMVC e Hibernate

Antes de iniciar o projeto é necessário criar o banco de dados com o nome sugerido de "casadocodigo", caso você opte por outro nome
será necessário fazer a alteração devida na classe JPAConfiguration. Nessa classe você deve alterar o nome de usuário do banco, a senha e
o nome do banco.

A porta de conexão do Apache Tomcat também foi alterada devido a conflitos com o MySQL Server, porém não irá atrapalhar a inicialização
e uso do projeto em outros locais.

Quando iniciar o projeto você deve executar a URL mágica (url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn)
no endereço raíz do projeto (localhost/casadocodigo/), essa URL adiciona alguns produtos no banco e cria um usuário com permissão ADMIN.

O Login com o usuário Admin é e-mail admin@casadocodigo.com.br e senha 123456.
Todos os usuários criados posteriormente terão a role USER por default. Sugiro que ao menos um usuário tenha a role ADMIN.
Caso ocorra de nenhum usuário ter a role ADMIN, você deve fazer a alteração diretamente no banco ou executar a url-mágica novamente.
