# Desafio Muxi
Desafio Android para o processo seletivo da empresa Muxi. O desafio consiste em consumir um JSON correspondente a uma lista de frutas. Cada elemento possui três campos: nome, preço em dolar e uma URL para sua respectiva imagem. Para saber mais sobre este desafio, clique [aqui](https://github.com/muxidev/desafio-android).

## Configuração
Algumas informações sobre versões utilizadas:
- Android Studio 3.0.1
- SDK minima 15 TargetVersion 27 ( Minima 19 para visualizar transições implementadas)
- NDK 18.0.5002713

## Desenvolvimento
Antes de começar a implementar, decidiu-se como seria a estrutura do projeto. Definiu-se então uma aplicação consistindo em três layouts: O primeiro que consiste basicamente em um botão para iniciar a aplicação. O segundo era a lista de frutas geradas a partir do JSON consumido, e o terceiro layout era o do perfil de cada fruta, contendo seus respectivos dados. Finalizada a decisão, optou-se por ferramentas para auxiliar tanto na comunicação com a API, quanto no download e cache das imagens oriundas dessa API. Para o consumo do JSON foi escolhido o framework [Volley](https://github.com/google/volley), mantida pela Google, possui vantagens como manter a requisição HTTP mesmo quando giramos uma Activity além da facilidade de uso. Para o cache de imagens, escolheu-se [Picasso](http://square.github.io/picasso/). Uma funcionalidade interessante é que a biblioteca possui um método onde é possivel verificar de onde o recurso está vindo através de pesquenas bordas coloridas nas imagens. Tal mecanismo auxiliou de forma bastante efetiva tanto para verificar se o cache estava realmente funcionando, quanto na questão do estado da aplicação ao mudar orientação. Com o JSON em mãos, o próximo passo seria calcular assincronamente o preco em reais e de forma nativa. Para o calculo assincrono se fez uso do [AsyncTask](https://developer.android.com/reference/android/os/AsyncTask) e para compilar o código nativo usou-se o [ndk-build](https://developer.android.com/ndk/guides/ndk-build).
