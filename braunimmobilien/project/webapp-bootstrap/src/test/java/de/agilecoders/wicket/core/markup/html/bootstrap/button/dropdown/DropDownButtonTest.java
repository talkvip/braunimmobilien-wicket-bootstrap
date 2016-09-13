package de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown;

import de.agilecoders.wicket.core.WicketApplicationTest;
import de.agilecoders.wicket.core.markup.html.bootstrap.behavior.AlignmentBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import de.agilecoders.wicket.core.markup.html.bootstrap.image.IconType;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * User: bcousin
 */
public class DropDownButtonTest extends WicketApplicationTest {
    @Test
    public void normalAlignmentDropDownButton() {
        tester().startComponentInPage(newDropDownButton());
        final TagTester tag = tester().getTagByWicketId("dropdown-menu");

        assertCssClass(tag, "dropdown-menu");
    }

    @Test
    public void rightAlignmentDropDownButton() {
        tester().startComponentInPage(newDropDownButton().setAlignment(AlignmentBehavior.Alignment.RIGHT));
        final TagTester tag = tester().getTagByWicketId("dropdown-menu");

        assertCssClass(tag, "dropdown-menu", "pull-right");
    }

    @Test
    public void leftAlignmentDropDownButton() {
        tester().startComponentInPage(newDropDownButton().setAlignment(AlignmentBehavior.Alignment.LEFT));
        final TagTester tag = tester().getTagByWicketId("dropdown-menu");

        assertCssClass(tag, "dropdown-menu", "pull-left");
    }

    @Test
    public void rightAlignmentSplitButton() {
        tester().startComponentInPage(newSplitButton().setAlignment(AlignmentBehavior.Alignment.RIGHT));
        final TagTester tag = tester().getTagByWicketId("dropdown-menu");

        assertCssClass(tag, "dropdown-menu", "pull-right");
    }

    private DropDownButton newSplitButton() {
        return new SplitButton("id", Model.of("")) {
            @Override
            protected AbstractLink newBaseButton(final String markupId, final IModel<String> labelModel,
                                                 final IModel<IconType> iconTypeModel) {
                return getDummyLink(markupId);
            }

            @Override
            protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                return Arrays.<AbstractLink>asList(getDummyLink(buttonMarkupId));
            }
        };
    }


    private DropDownButton newDropDownButton() {
        return new DropDownButton("id", Model.of("label")) {
            @Override
            protected List<AbstractLink> newSubMenuButtons(final String buttonMarkupId) {
                return Arrays.<AbstractLink>asList(getDummyLink(buttonMarkupId));
            }
        };
    }

    private BootstrapLink getDummyLink(final String buttonMarkupId) {
        return new BootstrapLink(buttonMarkupId, Buttons.Type.Menu) {

            @Override
            public void onClick() {
            }
        };
    }
}
