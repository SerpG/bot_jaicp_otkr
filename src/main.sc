require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /


    state: PinCode
        intent!: /pinCode
        if: $parseTree["_application"]
            a: Пин код для приложения 
        elseif: $parseTree["_card"]
            a: Пин код по карте
        else: 
            a: Сейчас расскажу порядок действий. Выберите, что именно планируете сделать:
            a: 1. Поменять пароль для входа в приложение.
            a: 2. Поменять PIN-код от карты.
            a: Пожалуйста, отправьте цифру, соответствующую вашему выбору.Или нажмите на кнопку
            buttons: 
                "Карта" -> /StatePath
                "Приложение" -> /PinCode/PinCodeСаrd
        
         state: PinCodeСаrd
            q: @card
            q: 2
            a: Это можно сделать в приложении:
                1. На экране "Мои деньги" в разделе "Карты" нажмите на нужную.
                2. Выберите вкладку "Настройки".
                3. Нажмите "Сменить пин-код".
                4. И введите комбинацию, удобную вам. 5. Повторите ее.

             
            
            



            

    state: Start
        q!: $regex</start>
        a: Начнём.

    state: Hello
        intent!: /привет
        a: Привет привет

    state: Bye
        intent!: /пока
        a: Пока пока

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

    state: Match
        event!: match
        a: {{$context.intent.answer}}