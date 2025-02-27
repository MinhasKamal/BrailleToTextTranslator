<h1> <img src="https://cloud.githubusercontent.com/assets/5456665/21795219/b66ae1c2-d72a-11e6-9c12-fb53adfa54c4.png" width="20" height=auto /> BrailleToTextTranslator </h1>

#### Translate Braille Writing to Plain Text

Braille is a specialized writing system for visually impaired people, where raised dots on embossed paper are used as tactile alphabet. The tool, Bengali Braille to Text Translator, will take in scanned image of Bengali Braille writing, apply pattern recognition, and translate it to text. Users do not need much theoretical or technical skill to run this software.

### Usage Scenario
Bengali Braille Character Recognizer is a tool that will take scanned image of Braille writing, run different types of image-preprocessing techniques, translate Braille to text through pattern recognition, and apply text correction procedures for final output.

Braille is a specialized writing system for visually impaired people. Here raised dots on embossed paper are used as tactile alphabet. These papers will be scanned with a scanner, and resulting image files will be stored in the computer.

At first, the user will select scanned images of Braille writing. Now, different types of image enhancement processes will be run. These image enhancement processes will improve Braille patterns and make it distinguishable from the 10 background. After that, various noise reduction algorithms will be applied for reducing artifacts and improve pattern quality. For further development of Braille dots, connectivity improvement and quantization will be applied.

In the second section, Braille is translated to text. Firstly, Braille patterns are extracted from the image. Then these patterns are converted to code- Braille Pattern Code (BPC- each dot of Braille character is encoded in ‘1’ and ‘0’). Now these resulting codes are mapped with Code to Character Map (CCM-like a hash table, where each unique code relates to only one character) and plain text file is generated. This text file contains probable raw characters for the Braille image. 

In the post processing stage, spell checking will be performed for minimizing error rate. Here, words for spell checking process will be stored in a Domain Word List (DWL) file. Lastly, a cleaning procedure will produce the final text file.

All these steps, settings, algorithms and their parameters can be automated using a configuration file- Braille Processing Template (BPT). Some templates (BPT) will be available to the user in default with the system. Users will also be able to define customized template.

### Demonstration
- [Video Demonstration](https://drive.google.com/file/d/0B54vrHge-bxdM3h2MmtRN3lYNHM/view?usp=sharing&resourcekey=0-p8C0-nURvHR2wa9OkCAtkA)

### Documentation
- Abstract- [document](https://www.slideshare.net/100005232690054/abstract-bengali-braille-to-text-translator)
- Project Proposal- [document](https://www.slideshare.net/100005232690054/project-proposal-bengali-braille-to-text-translation), [slide](https://www.slideshare.net/100005232690054/software-project-proposal-bengali-braille-to-text-translation)
- Software Requirement Specification- [document](https://www.slideshare.net/100005232690054/software-requirements-specification-on-bengali-braille-to-text-translator), [slide](https://drive.google.com/open?id=0B54vrHge-bxdbEVMWDhEa3NLTHM)
- Project Report- [document](https://www.slideshare.net/100005232690054/project-report-bengali-braille-to-text-translator), [slide](https://drive.google.com/open?id=0B54vrHge-bxdU2ZEYW9IODcxbG8)
- User Manual- [document](https://drive.google.com/open?id=0B54vrHge-bxdT2pjdGdQNG5yWWc)

### Project Source
- [Source Code](https://bitbucket.org/MinhasKamal/brailletotexttranslator)

### Publication

[Journal Paper](https://arxiv.org/abs/2012.01494)


### Cite This Work
```
@INPROCEEDINGS {10707851,
  author = {Minhas Kamal, Amin Ahsan Ali, Muhammad Asif Hossain Khan, Mohammad Shoyaib},
  title = {Braille to Text Translation for Bengali Language: A Geometric Approach},
  booktitle = {2018 Jahangir Nagar University Journal of Information Technology (JIT)},
  pages = {93-111},
  year = {2018}
}
```

See more of my research works [here](https://scholar.google.com/citations?user=SZxTaQgAAAAJ).

