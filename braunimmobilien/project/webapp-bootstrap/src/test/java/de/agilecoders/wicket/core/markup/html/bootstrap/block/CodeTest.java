package de.agilecoders.wicket.core.markup.html.bootstrap.block;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.Code;
import de.agilecoders.wicket.core.markup.html.bootstrap.block.CodeBehavior;

import org.apache.wicket.markup.Markup;
import org.apache.wicket.markup.MarkupException;
import org.junit.Test;

/**
 * Tests the {@link Code} class
 *
 * @author miha
 */
public class CodeTest extends WicketApplicationTest {

    @Test(expected = MarkupException.class)
    public void tagNameWasAsserted() {
        tester().startComponentInPage(new Code(id()));
    }

    @Test
    public void codeTagIsAllowed() {
        tester().startComponentInPage(new Code(id()), createMarkup("code"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

    @Test
    public void preTagIsAllowed() {
        tester().startComponentInPage(new Code(id()), createMarkup("pre"));

        tester().assertNoErrorMessage();
        tester().assertVisible(id());
    }

    @Test
    public void linenumberWithStartCssClassNameWasSet() {
        tester().startComponentInPage(new Code(id()).setStartFromLine(5), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "linenums:5");
    }

    @Test
    public void linenumberCssClassNameWasSet() {
        tester().startComponentInPage(new Code(id()).setShowLineNumbers(true), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "linenums");
    }

    @Test
    public void languageCssClassNameWasSet() {
        tester().startComponentInPage(new Code(id()).setLanguage(CodeBehavior.Language.XHTML), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "lang-xhtml");
    }

    @Test
    public void combinationOfCssClassNamesAreSet() {
        tester().startComponentInPage(new Code(id()).setLanguage(CodeBehavior.Language.XHTML).setStartFromLine(5), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "lang-xhtml", "linenums:5", "prettyprint");
    }

    @Test
    public void defaultCssClassNamesAreSet() {
        tester().startComponentInPage(new Code(id()), createMarkup("pre"));

        assertCssClass(tester().getTagByWicketId(id()), "prettyprint");
    }

    /**
     * creates default markup for CodeBehavior
     *
     * @param tagName The tag name to use
     * @return code markup
     */
    private Markup createMarkup(final String tagName) {
        return Markup.of("<" + tagName + " wicket:id=\"" + id() + "\">" + "content" + "</" + tagName + ">");
    }

}
