# zy-inc

Bom dia, conforme solicitado, foi desenvolvido um sistema capaz de:

  * cadastrar e armazenar coordernadas (nome,latitude,longitude ) 
  * buscar todas as coordernadas cadastradas 
  * buscar coordenadas proximas (dado um ponto de referencia e uma distancia maxima)

Esse sistema foi desenvolvido na linguagem *Java 8* (usando SpringBoot) e Thymeleaf para renderização das telas.
Para melhor estilizar as telas, coloquei Materializecss ( https://materializecss.com).

Esse sistema está hospedado nos servidores da Amazon (aws) e foi utilizado banco MySql (tambem na aws) para armazenar os dados.


Para acessar o sistema (que já hospedado) acesse: 
  * http://xyinc-app.us-west-2.elasticbeanstalk.com/
Lá tem todas as opções de Cadastro, Consulta e Pesquisa dos POIs, de forma intuitiva e simples. 



## Servicos de cadastro e consulta 

Caso não queira usar a tela, pode usar o serviço.
Para cadastrar uma coordenada atraves de um serviço, é só colocar os parametros na URL
 * http://xyinc-app.us-west-2.elasticbeanstalk.com/v1/coordenadas/cadastrarCoordenada/{nome}/{latitude}/{longitude}
  esse metodo retorna o JSON do objeto cadastrado
  
  
Para consultar todas coordenadas atraves do serviço, é só chamar a URL
 * http://xyinc-app.us-west-2.elasticbeanstalk.com/v1/coordenadas/listar
  esse metodo retorna o JSON de todos os objetos cadastrados
  
  
Para consultar os POIs proximos, baseado em uma determinada posição, atraves do serviço, é só colocar os parametros na URL
 * http://xyinc-app.us-west-2.elasticbeanstalk.com/v1/coordenadas/filtrarPOIs/{latitude}/{longitude}/{distanciaMax}
  esse metodo retorna o JSON com todos os POIs 
