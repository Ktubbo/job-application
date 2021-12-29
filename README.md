# job-application

Aby uruchomić aplikację należy utworzyć nowy schemat `Georeceiver` w bazie MySQL. Następnie utworzyć użytkownika `georeceiver_user` wraz z hasłem `georeceiver_Pass123` oraz nadać mu wszystkie uprawnienia dla schematu `Georeceiver`. Należy również utworzyć zmienną środowiskową `stripe_secret_test_key` o wartości klucza utworzonego dla posiadanego konta Stripe.

## 1. Abstrakcja klas drzew

![Tree abstraction](https://user-images.githubusercontent.com/67590859/147681672-0aacddc0-6111-4acf-82e9-7d903940eb90.png)

- Każde drzewo ma możliwość utworzenia nowego pnia (niektóre drzewa miewają więcej niż jeden pień), utworzenia nowych korzeni, rozprzestrzenienia nasion, przeprowadzenia fotosyntezy oraz pobrania składników odżywczych oraz wody z korzeni.
- Drzewa iglaste (Conifer tree) posiadają zaimplementowane metody interfejsu Tree.
- Drzewa liściaste (Leafy tree) posiadają wszystkie pola i metody drzew iglastych natomiast różnią się od drzew iglastych tym, że hibernują.
- Każde drzewo ma kolekcje pni oraz korzeni.
- Każdy pień ma kolekcje gałęzi.
- Każda gałąź ma kolekcje przedmiotów rosnących na gałęzi, takich jak liście czy nasiona.
- Każdy liść powinien mieć zaimplementowaną metodę odpowiedzialną za fotosynteze.
- Każde nasiono powinno mieć zaimplementowaną metodę odpowiedzialną za rozprzestrzenianie.

## 2. Usługa REST dla geolokalizacji


Aplikacja posiada: 
- obiekty domenowe Device, Geolocation,
- obiekty transferu danych DeviceDTO, GeolocationDTO
- mappery, które mapują obiekty domenowe na obiekty transferu danych i na odwrót,
- repozytoria, które mają za zadanie kontakt z bazą danych MySQL,
- serwisy bazodanowe, które odpowiadają za logikę aplikacji,
- kontrolery, które odpowiadają za komunikację z endpointami
- wyjątki, które są wyrzucane w przypadku nieistnienia w bazie danych, któregoś z obiektów,
- aspektowy logger, który loguje wszystkie użycia metod kontrolerów oraz zapisuje je w bazie danych
- testy jednostkowe wszystkich metod kontrolerów

![DeviceControllerTests](https://user-images.githubusercontent.com/67590859/147683864-f9d46d38-4aab-4f40-8348-44a2bfab8ba6.PNG)
![GeolocationControllerTests](https://user-images.githubusercontent.com/67590859/147683868-871ba676-be6d-46be-9443-db6b2cdb0854.PNG)

## 3. Implementacja biblioteki Stripe

Klasa `StripeService` obsługuje tworzenie faktur oraz ich pobieranie. Działanie serwisu jest potwierdzone dzięki testom jednostkowym tej klasy.

![StripeServiceTests](https://user-images.githubusercontent.com/67590859/147684466-a2005755-4761-4619-a48b-15476cbea151.png)
![Invoice](https://user-images.githubusercontent.com/67590859/147684471-5edc2204-844e-4301-881c-4f79e2765674.PNG)

