<!DOCTYPE html>
<html>
    <head>
        <title>Resultat</title>
    </head>
    <body>
        <h1>Dein BMI ist:</h1>
        <%-- Über request.getAttribute() kann der Wert abgefragt werden --%>
        <h2><% out.println(request.getAttribute("bmi"));%></h2>

        <%-- Eleganter lässt es sich mit Java Expression Language lösen --%>
        <h2>${bmi}</h2>
    </body>
</html>