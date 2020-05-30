# BinCommander
[![Donate with Bitcoin](https://en.cryptobadges.io/badge/small/12fApkUEecKA8UP6PAiNrGP1d2mvr1XXk9)](https://en.cryptobadges.io/donate/12fApkUEecKA8UP6PAiNrGP1d2mvr1XXk9)
[![Discord](https://img.shields.io/discord/658373639137132595?logo=discord&logoColor=white)](https://discord.gg/9wA2G8E)

Control your minecraft account over discord.

BinCommander uses Kotlin (JS), Discord.JS, Mineflayer and NodeJS to allow you to remotely control your minecraft account.

### Features
**Current**:
- Remotely and simultaneously connect multiple accounts to multiple servers
- Remotely see your coordinates and vitals
- Remotely see the latency and TPS of the server

**Planned**:
- Open a proxy server allowing you to take over control from a bot and switch between different bots and servers from
your local minecraft client
- Notifications on events such as Queue Server positions, damage, view distance etc
- Anti AFK, Auto Fish, Auto Mining, Long distance travelling

### Usage
Create a discord bot and add it to a server, with permissions to read and send messages.
Create `config.json` in the root repository folder, with the following template:
```json
{
    "discord": {
        "token": "YOUR_BOT_TOKEN",
        "prefix": "+"
    },
    "users": [
        {
            "discordID": "YOUR_DISCORD_USER_ID",
            "mcAccounts": [
                {
                    "mcName": "MinecraftUsername1",
                    "user": "Username or email for legacy accounts",
                    "pass": "password"
                },
                {
                    "mcName": "MinecraftUsername2",
                    "user": "Username or email for legacy accounts",
                    "pass": "password",
                    "clientToken": "You can optionally specify a token instead of a username/password combination"
                }
            ]
        }
    ]
}
```
Clone and execute `./gradlew run`.

## How?
This uses Kotlin with a JavaScript compilation target, allowing typesafe programming for nodejs. I have also written
bindings for all the libraries that I use to allow typesafe interoperability.
