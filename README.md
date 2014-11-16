Prosjekt-I
==========

Informatikk prosjekt I gruppe 10

Den ikke-tekniske brukermanualen vil forklare hvordan bruker/admin skal bruke programmet. Programmet er delt opp i to deler der den ene delen omfatter funksjonalitet til bruker og den andre omfatter funksjonalitet administrator skal ha tilgang til. Altså det er ikke to forskjellige programmer til admin og bruker, men funksjonaliteten er begrenset til hver av dem.

Kjøring av programmet
	finn .jar fila og kjør.

Oppstartsvindu 
Ved oppstart av programmet har man to faner oppe. I innloggingsfanen kan man opprette bruker/admin og man kan logge inn i systemet. Hvis man ikke har logget inn kan man uansett se informasjon om de forskjellige koiene og når de er ledig osv. 

Innlogging - Bruker
Hvis man som bruker vil lage en ny bruker i systemet, kan man gjøre det ved oppstart av programmet. Brukeren må ha en epostadresse[] som brukernavn og et passord[] som er på minst 6 tegn. Man vil få beskjed om brukeren ble opprettet eller ei. For at brukeren skal lage en bruker er det viktig at adminbrukerboksen[] ikke er krysset av. Når brukeren skal logge inn, skriver brukeren inn sin epostadresse og passord. Viktig at adminloginboksen[] ikke er krysset av. Når brukeren har logget inn, vil innloggingsfanen vise hvem som har logget inn, og man har en “Log ut” knapp[]. Når brukeren trykker på knappen[] vil brukeren logge ut av systemet.

Reserver Koie - Bruker
Når brukeren er logget inn kan brukeren reservere ei koie. Til høyre i vinduet[] vil brukeren se all informasjon om valgt koie. Brukeren kan velge koie[], bestemme dag[], måned[] og antall dager[] reservasjonen skal vare. Brukeren vil da se om den aktuelle tidsperioden er ledig[]. Brukeren trykker så på reserverknappen[] når han har funnet riktig dato. Brukeren kan reservere ei koie for 0-10 dager.

Reservasjoner - Bruker
Når en bruker har logget inn, har brukeren tilgang til å se sine reservasjoner. Reservasjonsfanen er delt opp i to deler. Reservasjoner[] og historie[]. I reservasjoner ser brukeren alle sine reservasjoner i framtiden[]. Her kan brukeren velge om han vil slette en bestemt reservasjon[]. I den andre kolonnen ser brukeren sin historikk. Her kan brukeren trykke på rapport[] på en bestemt reservasjon for å skrive en rapport om koia til systemet.

Rapport - Bruker
Når en bruker vil rapportere inn status til ei koie, vil et nytt vindu dukke opp. Dette vinduet er delt opp i tre faner; koieinfo, ødelagt utstyr og gjenglemt utstyr. Brukeren kan til en hver tid trykke avbryt og ingen informasjon blir lagret. Trykker brukeren på ok, vil all informasjon skrevet inn i rapporten bli sendt inn, og man kan ikke endre på dette etterpå.
Koieinfo
	I denne fanen får bruker opp informasjon om hvilken reservasjon brukeren har valgt å skrive rapport om. Brukeren kan her skrive inn antall brukte vedsekker[].
Ødelagt utstyr
	Her får bruker opp to kolonner med henholdsvis utstyr[] og ødelagt utstyr[]. Her kan brukeren markere et eller flere utstyr i en kolonne og flytte den over til den andre ved å trykke på knappene “Legg til”[] og “Fjern”[].
Gjenglemt utstyr
	Her kan brukeren skrive inn[] utstyr som brukeren har glemt igjen på koia. Brukeren skriver inn navnet[] og trykker legg til og det havner i gjenglemt utstyr kolonnen[]. Brukeren kan også her markere utstyr i ustyrskolonnen og fjerne dem.



Innloggin - administrator
Hvis du som admin vil lage en ny administrator, er det viktig at adminbrukerfeltet er krysset av. Admin skal ikke være en emailadresse, og adminbrukeren vil ikke bli godkjent hvis den inneholder en alfakrøll. Passordet må være på minst 6 bokstaver. Da vil admin få opp et felt med adminkode[] der man skal skrive inn en bestemt kode for adminregistrering[]. Man vil få en bemerkning om brukeren er opprettet i systemet. 
Når admin vil logge inn i systemet, er det viktig at adminloginboksen[] er avkrysset. Når admin har logget inn vil fanen vise at admin er logget inn, og man kan logge ut[]. Admin vil også i denne fanen ha mulighet til å tilbakestille databasen[]. Dette vil “henge” systemet i noen grad, siden det er ganske mange tabeller som skal tilbakestilles. All info i databasen vil bli slettet når databasen blir tilbakestilt, men alle tabeller vil bli opprettet på nytt.

Reserver koie - administrator
En administrator har ikke mulighet til å reservere ei koie på sin bruker, men administrator kan reservere ei koie for en annen bruker i systemet ved å skrive inn navnet til den aktuelle brukeren i feletet[]. 

Utstyrstatus - administrator
I denne fanen kan admin se utstyrsstatus til ei valgt koie. Fanen er delt opp i to, der admin kan velge koie[], og under se status på utstyret. Admin kan se om utstyr er i orden[], om det er ødelagt[], eller om det er gjenglemt utstyr[] koia.

Legg til utstyr - administrator
Her kan admin endre status på utstyr i ei bestemt koie, eller legge til utstyr. Admin velger først koie[]. Deretter kan admin legge til nytt utstyr hvis ønsket. Skriver inn navnet på det nye utstyret[], trykker deretter på “legg til”[] for å legge til utstyret i koia. Til venstre kan admin velge en ting[] i koia. Under denne kan admin bestemme status til det valgte utstyret[], enten IN_ORDER, BROKEN eller LOST_AND_FOUND. Til høyre er det et lite tekstfelt[] som vise valgt utstyr, og i hvilken tilstand det utstyret er i. Admin kan velge å fjerne det valgte utstyret ved å trykke på “Fjern”[] knappen. Admin kan velge å lagre endringene i databasen ved å trykke på “Lagre”[], eller admin kan resette alle endringene ved å trykke på “Reset”[]. Da vil ingen av endringene bli gjennomført.

Vedstatus - administrator
I denne fanen kan administrator se vedstatus[] til alle koiene i systemet. Hvis ei koie snart trenger veddugnad, vil det også stå[]. Admin kan velge ei bestemt koie[] og skrive legge til vedsekker til koia. Dette gjøres ved at admin skriver inn antall vedsekker[] og trykker på “Legg til”[]. Når “legg til” blir trykket på, vil dette oppdatere databasen med en gang.

Fjern reservasjoner - administrator
I denne fanen kan administrator velge å fjerne en eller flere reservasjoner til ei bestemt koie. Dette gjøres ved at admin først velger hvilken koie det er snakk om[], deretter vil admin få opp alle reservasjoner i ei git  koie[]. Admin kan da velge å slette en eller flere reservasjoner ved å trykke på “Slett”[]. Admin vil da få opp en en boks som bekrefter at denne reservasjonen skal slettes.

Mail til bruker - administrator
I denne fanen kan admin sende mail til en bestemt bruker i systemet. Dette kan være seg ting som skal tas med på ei koie elr lignende. Admin velger først hvilken bruker han skal sende til[], deretter skriver admin inn hva som skal stå i emnefeltet[] i mailen. Deretter skriver admin inn den informasjonen som brukeren skal få[]. Admin kan da velge å trykke på “Send”[] for å sende mailen, eller han kan trykke “Avbryt”[]. Ved trykk på “Avbryt” vil all tekst bli fjernet fra feltene.

Kart - administrator
I denne fanen kan administrator få opp relevant informasjon om ei bestemt koie. Fanen er delt opp i to, der på venstre side er det et kart[] over alle koiene. Til høyre[] er det et tekstfelt som viser all relevant informasjon om ei bestemt koie. Denne informasjonen får man ved å trykke på koiesymbolene på kartet[]. Hvert symbol representerer ei bestemt koie.
