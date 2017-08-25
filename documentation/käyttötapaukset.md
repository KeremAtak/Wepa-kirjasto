<h2>Käyttötapaukset</h2>

![kayttotapauskaavio](/documentation/kayttotapauskaavio.jpg)

<h3>Vierailijan tapaukset</h3>

<h5>Käyttäjän luonti</h5>
Vierailija luo käyttäjän millä hän pääsee käsiksi palveluun käyttäjänä. Syötteet validoidaan niin että käyttäjänimi on uniikki
ja syötteiden pituudet ovat oikein.

<h5>Kirjautuminen</h5>
Vierailija voi kirjautua palveluun joko käyttäjänä tai adminina.

<h3>Käyttäjän tapaukset</h3>

<h5>Kirjan varaus</h5>
Käyttäjä voi varata itselleen kirjan. Kirja haetaan genren mukaan. Varatut kirjat löytyvät käyttäjän varaussivulta.

<h5>Kirjan palautus</h5>
Käyttäjä voi poistaa varauksensa varaussivulla.

<h5>Tiedon selaus</h5>
Käyttäjä voi selata kirjoja, genrejä ja kirjailijoita. Lisäksi kirjailija voi selata omia varauksiaan.

<h3>Adminin tapaukset</h3>

<h5>Kirjan/genren/kirjalijan luonti</h5>
Admin voi luoda kirjoja/genrejä/kirjailijoita. Näitä luodessa syötteet validoidaan ja pituusehtojen täytyy täyttyä. Genren 
nimi pitää olla uniikki mutta kirjan ja kirjailijan eivät tarvitse olla. Kirjan luomiseen vaaditaan se, että tietokannoista 
löytyy ainakin yksi genre ja kirjailija. 

<h5>Kirjan/genren/kirjalijan/käyttäjän poisto</h5>
Admin voi poistaa kirjoja/genrejä/kirjailijoita/käyttäjiä. Genreä/kirjailijaa poistettaessa niiden kirjat poistuvat. 
Kirjaa/käyttäjää poistettaessa sen varaukset poistuvat.

<h5>Tiedon selaus</h5>
Admin voi selata kirjoja, genrejä, kirjailijoita ja käyttäjiä. Näillä sivuilla adminilla on oikeus lisätä/poistaa tietoa.
