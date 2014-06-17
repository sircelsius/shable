# Bienvenue sur le git de shable #

## Project ##

Shable is the exam management utility you have always dreamt of. Using optimal treatment algorithms, Shable places your student in your exam rooms in the best possible way.

Furthermore, Shable has an integrated database which enables you to define classrooms and student lists that will be saved for future use!


## Team ##

[Christophe Guillonnet](http://gitlab.etude.cergy.eisti.fr/u/christophe.guillonnet) - Java EE developer / gold miner in algorithmics.

[Marc Bramaud du Boucheron](http://gitlab.etude.eisti.fr/u/marc.bramaud.du.boucheron) - Java EE developer / gold miner in UX/UI.

## Requirements ##

You will need `mariaDB` if you want to mess around with shable.

For development, `Node.js` (available [here](https://github.com/joyent/node)) is necessary.

You will also be needing `grunt` (get it via `npm` or [here](http://gruntjs.com/)).

### A little note on using `node`, `npm` `grunt` and other packages ###

At EISTI, we have a nice little proxy setup, which prevents us lowly students from downloading illegal material using the school's internet.

The problem is it also messes up our command line installations, even if the proxy is properly exported to your `HTTP_PROXY` path.

Supposing you have `npm` installed, you want to run your install commands with the following option:

	npm --proxy http://user:password@proxy.cergy.eisti.fr:3128 install -g grunt-cli

This example will globally install the `grunt-cli` on your pc.




## Changelog ##

03/27 - Started implementing classroom display with `sigma.js`.
03/26 - Switched from `Kinetic.js` to [sigma.js](https://github.com/jacomyal/sigma.js) for better graph maniability, started displaying classrooms, translated README.md
03/21 - Started integrating [Kinetic.js](http://kineticjs.com/) to display classrooms.
03/02 - Added the dashboard, started integrating the database using `Hibernate`.
03/02 - Added icon, added `modernizer.css`, corrected broken links.
02/24 - Created database creation scripts (script `/database/create.sql`).
02/23 - Created `shable/home`, `shable/login` and `shable/statut`.


## TODO ##

	1. Attribute icon [Programmer designed by Hadi Davodpour from the Noun Project](http://thenounproject.com/hadivoice)
	2. Translate pages from french to english


