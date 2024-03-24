require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /


    state: PinCode
        intent!: /pinCode
        if: $parseTree["_application"]
            go!: /PinCode/PinCodeApplication
        elseif: $parseTree["_card"]
            go!: /PinCode/PinCodeСаrd
        else: 
            a: Сейчас расскажу порядок действий. Выберите, что именно планируете сделать:
                1. Поменять пароль для входа в приложение.
                2. Поменять PIN-код от карты.
                Пожалуйста, отправьте цифру, соответствующую вашему выбору.Или нажмите на кнопку
            buttons: 
                "Карта" -> /PinCode/PinCodeApplication
                "Приложение" -> /PinCode/PinCodeСаrd
        
        state: PinCodeСаrd
            q: @card
            q: 2
            a: Это можно сделать в приложении:
                1. На экране "Мои деньги" в разделе "Карты" нажмите на нужную.
                2. Выберите вкладку "Настройки".
                3. Нажмите "Сменить пин-код".
                4. И введите комбинацию, удобную вам. 
                5. Повторите ее.
            a: И все готово!
               Пин-код установлен, можно пользоваться. 🙂


        state: PinCodeApplication
            q: @application
            q: 1
            a: Смена пароля от приложения возможна несколькими способами:
                1. на экране "Профиль" выберите "Изменить код входа в приложение".
                2. введите SMS-код.
                3. придумайте новый код для входа в приложение и повторите его.
                



            

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
        
        
        
    state: SetAlarm
        q!: будильнк
        a: На какое время?
    
        state: Time
            q: * @duckling.time *
            script:
                $temp.time = $parseTree["_duckling.time"];
                $pushgate.createEvent($temp.time.value);
            a: Будильник установлен на {{$temp.time.hour}}:{{$temp.time.minute}}.
    
    state: Alarm
        event!: timerEvent
        a: Пора вставать!