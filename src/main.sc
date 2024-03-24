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
            a: пин код
            
            
        
            


            

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