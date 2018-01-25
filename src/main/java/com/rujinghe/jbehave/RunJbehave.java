package com.rujinghe.jbehave;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.rujinghe.jbehave.stepdefs.EmptySteps;

/**
 * @author He Rujing
 *
 */
public class RunJbehave extends JUnitStories {

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
				.usePendingStepStrategy(new FailingUponPendingStep())
				.useStoryLoader(new LoadFromClasspath(getClass()))
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
						.withDefaultFormats()
						.withFormats(Format.CONSOLE, Format.TXT, Format.HTML));
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(
				CodeLocations.codeLocationFromClass(getClass()),
				"jbehave/example/**/*.story",
				null);
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new EmptySteps());
	}

}
