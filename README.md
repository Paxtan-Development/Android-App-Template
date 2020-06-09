# Android-App-Template
[![Release](https://badgen.net/github/release/Paxtan-Development/Android-App-Template/stable)](https://github.com/Paxtan-Development/Android-App-Template/releases)
[![Build Status](https://drone.pcchin.com/api/badges/Paxtan-Development/Android-App-Template/status.svg)](https://drone.pcchin.com/Paxtan-Development/Android-App-Template)
[![Lines of Code](https://badgen.net/codeclimate/loc/Paxtan-Development/Android-App-Template)](/)
[![Maintainability](https://api.codeclimate.com/v1/badges/PLACEHOLDER/maintainability)](https://codeclimate.com/github/Paxtan-Development/Android-App-Template/maintainability)
[![Technical Debt](https://badgen.net/codeclimate/tech-debt/Paxtan-Development/Android-App-Template)](https://codeclimate.com/github/Paxtan-Development/Android-App-Template/)

A generic template that can be used to create Android apps. It includes Sentry integration and some basic functionality.

## Usage
To use this template, you would need to set up the following environment variables under a `keystore.properties` file in the root directory of the project:
- `keystoreDir`: The file containing your keystore.
- `keystoreAlias`: The alias for your keystore.
- `keystorePass`: The password to access your keystore.
- `sentryDsn`: The DSN to access your Sentry instance (Optional).

Then, just change the app package name and you should be good to go!

## Installation
The apk files can be found at the [releases](https://github.com/Paxtan-Development/Android-App-Template/releases) page.

If you wish to compile the apk yourself, you can compile it directly from the [source code](https://github.com/Paxtan-Development/Android-App-Template/releases).
However, do publish the app under a different package name to avoid any conflict with this app.

## Contribution
Any contribution is welcome, feel free to add any issues or pull requests to the repository.

Before you contribute, do read through the [Code of Conduct](/CODE_OF_CONDUCT.md).

## License
Enter license details here. All licenses for the media / libraries used can be found in the About page of the compiled app.