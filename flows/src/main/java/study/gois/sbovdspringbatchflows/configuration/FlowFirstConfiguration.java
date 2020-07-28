package study.gois.sbovdspringbatchflows.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowFirstConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step myStepFirst() {
        return stepBuilderFactory.get("myStepFirst")
                .tasklet( (stepContribution, chunkContext) -> {
                    System.out.println("myStepFirst was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job flowFirstJob(Flow flow) {
        return jobBuilderFactory.get("flowFirstJob")
                .start(flow)
                .next(myStepFirst())
                .end()
                .build();
    }
}
