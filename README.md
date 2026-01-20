# 💩 CodeShittifier

**The anti-formatter your code deserves (but definitely doesn't need)**

Ever looked at perfectly formatted code and thought "this is too clean, too readable, too... *good*"? No? Well, too bad. CodeShittifier is here anyway.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Chaos Level](https://img.shields.io/badge/Chaos%20Level-MAXIMUM-red.svg)](https://github.com/yourusername/CodeShittifier)
[![Code Quality](https://img.shields.io/badge/Code%20Quality-Terrible-red.svg)]()

## What The Hell Is This?

CodeShittifier is a code formatter for Java and Kotlin that does the exact opposite of what every other formatter does. While tools like Prettier and ktfmt make your code beautiful, CodeShittifier takes your beautiful code and turns it into a dumpster fire. Why? Because sometimes you need to:

- Prove to your manager that code formatting actually matters
- Test if your fancy code formatter can handle absolute garbage
- Show junior devs what happens when you ignore the style guide
- Prank your coworker who keeps bragging about their "clean code"
- Create the worst possible code examples for your blog post
- Exact revenge on that one guy who keeps force-pushing to main

I originally built this because I got tired of explaining why tabs vs spaces matters to a teammate who insisted "it's all the same to the compiler." Well buddy, meet CodeShittifier. Compiler doesn't care, but your eyeballs sure will.

## ⚠️ Legal Disclaimer (Yes, We Need One)

This is a joke. A fully functional, compiles-perfectly joke, but still a joke. Using this on production code is like using a flamethrower to light birthday candles - technically possible, deeply inadvisable.

I'm not responsible for:
- Your coworkers refusing to work with you
- Getting kicked out of code review
- HR wanting a "chat" about professionalism
- Your IDE filing a restraining order
- Mental anguish from looking at the output
- Your cat leaving you (unrelated, but still not my fault)

## Installation

There is no installation. It's one Java file. Download it. Compile it. Ruin some code. This isn't rocket science.

```bash
# Download it however you want
curl -O https://raw.githubusercontent.com/yourusername/CodeShittifier/main/CodeShittifier.java

# Compile (if you can't do this, you have bigger problems)
javac CodeShittifier.java

# Ready to cause problems
```

## How To Use It

```bash
# ALWAYS dry-run first unless you enjoy pain
java CodeShittifier --dry-run --level 5 ./src

# Actually mess up your code (don't say I didn't warn you)
java CodeShittifier --level 5 ./src/main/java

# For the truly unhinged
java CodeShittifier --level 10 ./myproject

# Just one file because you're not completely insane
java CodeShittifier --level 3 MyClass.java
```

### Command Line Options

```
-d, --dry-run       Preview the chaos without commitment (recommended)
--no-backup         Live dangerously (not recommended)
-l, --level <1-10>  How bad do you want it? (default: 5)
-v, --verbose       Watch it destroy your code in real-time
```

## The Chaos Levels Explained

**Level 1-2: "It's Fine"**  
Random indentation, some spacing weirdness. Your code looks like it was formatted by someone who just discovered the space bar. Annoying but survivable.

**Level 3-4: "Hmm, This Is Bad"**  
Everything from before plus brace styles that follow no known standard. K&R? Allman? How about "whatever pisses you off most"?

**Level 5-6: "Who Hurt You?"**  
Now we're adding random blank lines where they don't belong and removing them where they should be. Plus operator spacing that makes algebra teachers cry.

**Level 7-8: "I Can't Read This"**  
Trailing spaces (yes, on purpose), tabs mixed with spaces like a monster, and spacing around operators that follows no logic whatsoever. This is where code reviews go to die.

**Level 9-10: "Why Does This Even Compile?"**  
All of the above, PLUS we break your lines at random safe points. Your 80-character line? Now it's spread across 3 lines for absolutely no reason. Still compiles though. That's the beauty of it.

## Before and After

### Your Code Now (Beautiful)
```java
public class BankTransfer {
    
    public boolean transfer(Account from, Account to, Money amount) {
        if (from.getBalance().isGreaterThan(amount)) {
            from.debit(amount);
            to.credit(amount);
            return true;
        }
        return false;
    }
}
```

### Your Code After Level 5 (Cry)
```java
public class BankTransfer
{

		public boolean transfer(Account from,Account to  ,  Money amount){
if(from.getBalance().isGreaterThan(amount))
		{
from.debit(amount)   ;
	to.credit(amount);
			return true  ;
	}
	return false   ;
		}
}
```

### Your Code After Level 10 (Seek Help)
```java
public class BankTransfer
{

				public boolean transfer(Account from  ,  Account 
to,Money amount)
{
if(from.getBalance().
isGreaterThan(amount)){
		from.debit(amount)    ;
to.credit(
amount)   ;
			return true;
			}
return false  ;
			}
}
```

Yeah. It still compiles. I tested it. Your IDE will hate you, but Java doesn't judge.

## Why Does This Exist?

### Legitimate Uses:
1. **Teaching tool** - Show people why formatting standards exist
2. **Formatter testing** - Does your formatter handle absolute garbage?
3. **Code review training** - "Find what's wrong with this code"
4. **Blog content** - Need examples of terrible code? Got you covered
5. **Stress testing IDEs** - Will IntelliJ's auto-format survive this?

### Probably Don't Use It For:
1. Production code (obviously)
2. Your open source contributions (please)
3. That startup job interview (trust me)
4. Anything involving money
5. Anything involving human safety
6. Anything, really

## How It Works

The tool walks through your Java/Kotlin files and applies various "shittification strategies":

1. **Indentation Chaos** - Tabs? Spaces? Both? Who cares! Random counts of each!
2. **Spacing Roulette** - Operators get spaces. Sometimes. Randomly.
3. **Brace Style Roulette** - Opening braces on the same line? Next line? Let's flip a coin!
4. **Blank Line Generator** - Add random blank lines. Remove useful ones. Maximum confusion.
5. **Smart Line Breaking** - Breaks your lines at "safe" points (after operators/commas) so it still compiles, just looks horrible.

The "smart" part is important - it never breaks import statements, package declarations, or string literals. Your code will look like garbage but still compile perfectly. This is a feature, not a bug.

## Safety Features

Look, I'm not a monster. The tool has safety features:

- ✅ Creates a timestamped backup folder before doing anything
- ✅ Never breaks `import` or `package` statements  
- ✅ Preserves string literals (we're not savages)
- ✅ Won't break annotations
- ✅ Only breaks lines at safe points (after operators/commas)
- ✅ Has a dry-run mode so you can preview the chaos

If you run it without `--dry-run` and without git/backups, that's on you.

## Real Talk: Educational Value

Okay, serious moment. This tool actually is useful for:

**Teaching Code Quality** - There's no better way to show why formatting matters than showing what happens when it doesn't. Run this on a clean file, show it to a junior dev, and watch them have an epiphany about why we have ESLint/ktlint rules.

**Testing Formatters** - If you're building a code formatter, this generates excellent test cases. Can your formatter handle code that's been through CodeShittifier Level 10? If yes, it can probably handle anything.

**Understanding Tools** - Building this taught me way more about how formatters work than reading documentation ever did. Sometimes you learn more from breaking things than fixing them.

## Known Issues

- None. This is working exactly as intended. If your code looks terrible, that's not a bug, that's the feature.
- Actually, there is one: it makes your code ugly. But again, that's the point.

## FAQ

**Q: Will this break my code?**  
A: No, it only changes formatting. Your code will compile and run exactly the same. It'll just look like it was formatted by a drunk raccoon.

**Q: Can I reverse it?**  
A: Yes. The tool creates a backup folder. Use that. Or just run your normal formatter on it. Or restore from git like a responsible adult.

**Q: Why Java 17+?**  
A: Because I used `String.repeat()` and `.stripLeading()` and I'm not rewriting it for Java 8. Update your JDK, it's 2026.

**Q: Does it work on Kotlin?**  
A: Yes. It makes Kotlin look just as bad as Java. Equality achieved.

**Q: What if I run it twice?**  
A: It'll get worse. Then worse again. There's no lower limit. I tested it at Level 10 three times in a row and my IDE crashed.

**Q: Is there a way to make it EVEN worse?**  
A: I'm working on it. Next version might include random Unicode whitespace characters and emoji operators. Stay tuned.

**Q: Can you add a deshittifier?**  
A: Just use google-java-format or ktfmt. That's literally what they're for.

## Contributing

Want to make it worse? Pull requests welcome! 

Ideas I'm considering:
- Random comment injection ("// TODO: Why does this work?")
- Method name case randomization (keeping it compilable)
- Unnecessary parentheses everywhere
- Random newlines inside string concatenation (still valid Java!)
- Variable name "typos" that are still valid (user → usr → usrr)

If you have ideas for making code even harder to read while keeping it compilable, open an issue. Let's make something terrible together.

## Roadmap

- [x] Make code look terrible
- [x] Keep it compilable
- [x] Add 10 levels of chaos
- [ ] Gradle plugin (for CI/CD chaos)
- [ ] IntelliJ plugin (for live chaos)
- [ ] Android Studio integration
- [ ] Maven plugin (for build-time chaos)
- [ ] Shittification profiles (Enterprise Java™ style, anyone?)
- [ ] Web playground (paste code, get garbage back)
- [ ] VS Code extension (real-time shittification)
- [ ] GitHub Action (automatically worsen PRs - don't actually do this)

## Credits

- Inspired by [Shittier](https://github.com/rohitdhas/shittier), the JavaScript version
- Built out of frustration with a coworker who said "formatting doesn't matter"
- Tested on 10 years of legacy banking code (sorry, former colleagues)
- Thanks to all the code formatters I'm actively opposing
- Special shoutout to whoever decided tabs vs spaces should be a holy war

## License

MIT License. Because even terrible ideas deserve to be free.

Use it for good (teaching), use it for evil (pranks), just don't blame me when your team stops talking to you.


### Star This Repo If:
- You've worked on legacy code
- You've argued about tabs vs spaces
- You've seen a 5000-line method with no formatting
- You appreciate chaos
- You hate yourself (kidding, please don't)

### Don't Star This Repo If:
- You actually want to use this in production
- You're a code quality purist who can't take a joke
- You're my former manager (sorry Steve)

💩 **Happy Shittification!** 💩
