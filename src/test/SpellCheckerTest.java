package test;

import com.minhaskamal.brailleToTextTranslator.processes.postProcessing.process.SpellChecker;

public class SpellCheckerTest  extends BrailleToTextTranslatorTest{

	public static void main(String[] args) {
		new SpellCheckerTest().runTest();
	}
	
	@Override
	public void testMethod() throws Exception {
		SpellChecker spellChecker = new SpellChecker("src\\res\\txt\\BengaliWordList.dwl");
		
		System.out.println(spellChecker.checkSpellOfAWord("অংশ"));
		System.out.println(spellChecker.checkSpellOfAWord("অ#শ"));
		System.out.println(spellChecker.checkSpellOfAWord("দেহ"));
		System.out.println(spellChecker.checkSpell("অংশ, অ#শ, দ#হ দে## অ#ঠিন অকপ#"));
	}

}
