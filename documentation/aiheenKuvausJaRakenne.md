<h2>Wepa-kirjasto</h2>

<h3>Aihe:</h3>
Wepa-kirjasto on yksinkertainen kirjastopalvelu missä käyttäjät voivat lainata itselleen kirjoja.

<h3>Toiminnot:</h3>
<h5>Vierailija:</h5>
Vierailijalla ei ole oikeuksia sivustoon. Hänen täytyy luoda itselleen käyttäjä. Käyttäjää luodessa validoidaan luodun käyttäjän
syötteet: käyttäjän nimi täytyy olla uniikki, ja nimen sekä salasanan pituudet pitää täyttää ehdot.

<h5>Käyttäjä:</h5>
Käyttäjä voi lainata itselleen kirjoja. Lainaukseen liittyy aina yksi kirja. Lainattua kirjaa ei voi kukaan muu lainata ennen
kuin lainaaja palauttaa sen. Kirjaan liittyy genre ja kirjailija, ja käyttäjä voi etsiä itselleen kirjoja genren ja kirjailijan
mukaan.

<h5>Admin:</h5>
Adminilla on oikeudet lisätä/poistaa genrejä, kirjailijoita ja genrejä. Näitä luodessa syötteet validoidaan
Kirja luodaan genre-sivulla minne ei pääse käsiksi jos kirjailijoita ei löydy tietokannasta.

Käyttäjä voi poistaa kirjoja milloin kirjaan liittyvät varaukset poistuvat. Genren tai kirjailijan poistettaessa 
niihin liittyvät kirjat poistetaan. Admin ei voi varata kirjoja, tarkastella käyttäjien varauksia eikä poistaa käyttäjien
varauksia.

<h3>Käyttötapaukset:</h3>
