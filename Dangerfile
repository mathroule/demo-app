# Sometimes it's a README fix, or something like that - which isn't relevant for
# including in a project's CHANGELOG for example
declared_trivial = github.pr_title.include? "#trivial"

# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
if github.pr_title.include? "[WIP]"
    warn("PR is classed as Work in Progress")
    auto_label.wip=(github.pr_json["number"])
else
    auto_label.remove("WIP")
end

if github.pr_title =~ /[A-Z]+-[0-9]+:/
    warn("PR is classed as Work in Progress")
    auto_label.wip=(github.pr_json["number"])
else
    warn("PR label does not match pattern 'DEMO-123: PR label'")
end

# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
fail("PR has invalid name") if github.pr_title.include? "[WIP]"

# Don't let testing shortcuts get into master by accident
fail("fdescribe left in tests") if `grep -r fdescribe specs/ `.length > 1
fail("fit left in tests") if `grep -r fit specs/ `.length > 1

# Add a CHANGELOG entry for app changes
#if !git.modified_files.include?("CHANGELOG.md") && has_app_changes
#  fail("Please include a CHANGELOG entry. \nYou can find it at [CHANGELOG.md](https://github.com/realm/jazzy/blob/master/CHANGELOG.md).")
#  message "Note, we hard-wrap at 80 chars and use 2 spaces after the last line."
#end

# Ensure a clean commits history
if git.commits.any? { |c| c.message =~ /^Merge branch/ }
  fail('Please rebase to get rid of the merge commits in this PR')
end

# AndroidLint
android_lint.gradle_task = 'lintRelease'
android_lint.report_file = 'app/build/reports/lint/lint-results.xml'
android_lint.lint(inline_mode: true)

# Checkstyle
checkstyle_format.base_path = Dir.pwd
checkstyle_format.report 'app/build/reports/checkstyle/checkstyle.xml'

# Findbugs
findbugs.gradle_task = 'findbugs'
findbugs.report_file = 'app/build/reports/findbugs/findbugs.xml'
findbugs.report

# JUnit tests
#junit.parse 'app/build/test-results/testReleaseUnitTest/*.xml'
#junit.show_skipped_tests = true
#junit.report
