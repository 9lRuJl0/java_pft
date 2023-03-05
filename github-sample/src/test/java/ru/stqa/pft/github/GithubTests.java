package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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 ");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("9lRuJl0", "java_pft")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) { //построили пустой набор пар
            System.out.println(new RepoCommit.Smart(commit).message()); // выводим все коммиты на консоль
        }
    }
}