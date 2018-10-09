# Desafio Muxi
Desafio Android para o processo seletivo da empresa Muxi. O desafio consiste em consumir um JSON correspondente a uma lista de frutas. Cada elemento possui três campos: nome, preço em dolar e uma URL para sua respectiva imagem. Para saber mais sobre este desafio, clique [aqui](https://github.com/muxidev/desafio-android).

## Configuração
Algumas informações sobre versões utilizadas:
- Android Studio 3.0.1
- SDK minima 15 TargetVersion 27 
- NDK 18.0.5002713

## Desenvolvimento
Antes de começar a implementar, decidiu-se como seria a estrutura do projeto. Definiu-se então uma aplicação consistindo em três layouts: O primeiro que consiste basicamente em um botão para iniciar a aplicação. O segundo era a lista de frutas geradas a partir do JSON consumido, e o terceiro layout era o do perfil de cada fruta, contendo seus respectivos dados. Finalizada a decisão, optou-se por ferramentas para auxiliar tanto na comunicação com a API, quanto no download e cache das imagens oriundas dessa API. Para o consumo do JSON foi escolhido o framework [Volley](https://github.com/google/volley), mantida pela Google, possui vantagens como manter a requisição HTTP mesmo quando giramos uma Activity além da facilidade de uso. Para o cache de imagens, escolheu-se [Picasso](http://square.github.io/picasso/). Uma funcionalidade interessante é que a biblioteca possui um método onde é possivel verificar de onde o recurso está vindo através de pesquenas bordas coloridas nas imagens. Tal mecanismo auxiliou de forma bastante efetiva tanto para verificar se o cache estava realmente funcionando, quanto na questão do estado da aplicação ao mudar orientação. Com o JSON em mãos, o próximo passo seria calcular assincronamente o preco em reais e de forma nativa. Para o calculo assincrono se fez uso do [AsyncTask](https://developer.android.com/reference/android/os/AsyncTask) e para compilar o código nativo usou-se o [ndk-build](https://developer.android.com/ndk/guides/ndk-build). Para manter o estado mudando a orientação foi adicionado o seguinte código ao `AndroidManifest.xml`: 


```
<activity
    android:name=".Nome_Activity"
    android:configChanges="orientation|screenSize">
</activity>
```
Utilizou-se o [Material Design](https://material.io/design/) para definir um botão de inicializar, uma lista para exibir as frutas e uma transição entre a lista e o perfil da fruta dando a sensação de continuidade. Para essa transição é necessária api >= 21. Para animações inseridas api >= 19

## Testes
Para a realização dos testes utilizou-se o [Junit4](https://junit.org/junit4/) para testes unitários e para testes instrumentados o [Espresso](https://developer.android.com/training/testing/espresso/). 

### Testes unitários
O teste consiste em verificar se o arredondamento de casas decimais do preço em real é feito de forma correta. Definiu-se que o numero de casas decimais seriam duas, logo se a terceira casa decimal for >= 5, deve-se acrescentar em 1 a segunda casa decimal.
Para executar o teste verifique se está na janela `Project` -> `Java`-> Selecione o pacote correspondente a `(test)`-> Clique com botão direito -> `Run`

### Testes instrumentados
Esses são os testes de interface, que navegam pela aplicação. O teste consiste em navegar desde a tela inicial até a tela de perfil da fruta. Ao final, é comparado se o texto exposto com o nome da fruta é realmente o correto. Para elaborar esse teste utilizou a funcionalidade `Record Espresso Test`. Observou-se que para um melhor desempenho do teste se fez necessário desativar animações do dispositivo nas opções de desenvolvedor. Para executar o teste verifique se está na janela `Project` -> `Java`-> Selecione o pacote correspondente a `(androidTest)`-> Clique com botão direito -> `Run`

## Gerar APK
Para gerar o apk vá em `Build`-> `Generate Signed APK`-> `Create new`-> Escolhe-se a pasta para o arquivo `.jks`e preenche os devidos campos -> `Ok`-> Marque o tipo de assinatura V1 (Jar Signature) V2 (Full APK Signature). O projeto contem um apk ja gerado localizado [aqui](https://github.com/Theusma94/desafioMuxi/blob/master/app/release/app-release.apk)
