package net.sourceforge.plantuml.test.playground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.sourceforge.plantuml.FileFormat.PNG;
import static net.sourceforge.plantuml.security.SecurityProfile.UNSECURE;
import static net.sourceforge.plantuml.security.SecurityUtils.getSecurityProfile;
import static net.sourceforge.plantuml.test.playground.Data.ALL;
import static net.sourceforge.plantuml.test.playground.Data.PLAYGROUND_OUTPUT_DIR;
import static net.sourceforge.plantuml.test.playground.Group.testGroup;
import static org.assertj.core.api.Assertions.assertThat;

public class PlaygroundTest {

	@BeforeEach
	public void beforeEach() {
		RenderPlantuml.suppressRenderExceptions = false;
	}

	@Test
	public void testRenderAll() throws Exception {
		assertThat(getSecurityProfile())
			.as("Must set env var PLANTUML_SECURITY_PROFILE=UNSECURE")
			.isEqualTo(UNSECURE);

		ALL.render();

		ALL.stream().forEach(n ->
			assertThat(PLAYGROUND_OUTPUT_DIR.file("rendered/" + n + ".png").conv()).isNotEmpty()
		);
	}

	@Test
	public void testGrid() throws Exception {
		testGroup("class", "sequence")
			.render(PNG, "plain")
			.render(PNG, "styled",
				"skinparam DiagramBorderColor red",
				"skinparam BackgroundColor blue"
			)
			.markdownHorizontal("test-h", "plain", "styled")
			.markdownVertical("test-v", "plain", "styled")
			.pngHorizontal("test-h", "plain", "styled")
			.pngVertical("test-v", "plain", "styled");

		assertThat(PLAYGROUND_OUTPUT_DIR.file("test-h.md").conv()).hasContent("" +
			"| | `class` | `sequence` |\n" +
			"|---|---|---|\n" +
			"| `plain` | ![](plain/class.png) | ![](plain/sequence.png) |\n" +
			"| `styled` | ![](styled/class.png) | ![](styled/sequence.png) |\n" +
			""
		);

		assertThat(PLAYGROUND_OUTPUT_DIR.file("test-v.md").conv()).hasContent("" +
			"| | `plain` | `styled` |\n" +
			"|---|---|---|\n" +
			"| `class` | ![](plain/class.png) | ![](styled/class.png) |\n" +
			"| `sequence` | ![](plain/sequence.png) | ![](styled/sequence.png) |\n"
		);

		assertThat(PLAYGROUND_OUTPUT_DIR.file("test-h.png").conv()).isNotEmpty();

		assertThat(PLAYGROUND_OUTPUT_DIR.file("test-v.png").conv()).isNotEmpty();
	}
}
