# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

# Warn when PR title does not match pattern
if !(github.pr_title =~ /[A-Z]+-[0-9]+:/)
    fail("PR has invalid name and does not match pattern 'DEMO-123: PR label'")
end

# Warn when PR body is empty
warn("PR body is empty") if github.pr_body.empty?

# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

# Ensure a clean commits history
if git.commits.any? { |commit| commit.message =~ /^Merge branch/ }
  fail('Please rebase to get rid of the merge commits in this PR')
end

# Add a CHANGELOG entry for app changes
has_app_changes = !git.modified_files.grep('/app/src/main/').empty?
if !git.modified_files.include?('CHANGELOG.md') && has_app_changes
  fail("Please include a CHANGELOG entry. \nYou can find it at [CHANGELOG.md](https://github.com/mathroule/demo-app/blob/master/CHANGELOG.md).")
  message "Note, we hard-wrap at 80 chars and use 2 spaces after the last line."
end

# Request tests for app changes
has_test_changes = !git.modified_files.grep('/app/src/test/').empty? || !git.modified_files.grep('/app/src/androidTest/').empty?
if has_app_changes && !has_test_changes
  warn("There're app changes, but not tests", sticky: false)
end

# APK stats
apkstats.apk_filepath = 'app/build/outputs/apk/release/app-release-unsigned.apk'
apkstats.compare_with('sample/app-release-unsigned.apk', do_report: true)

# Android lint
android_lint.gradle_task = 'lintRelease'
android_lint.report_file = 'app/build/reports/lint/lint-results.xml'
android_lint.lint(inline_mode: true)

# Commit lint
commit_lint.check

# Checkstyle
checkstyle_format.base_path = Dir.pwd
checkstyle_format.report 'app/build/reports/checkstyle/checkstyle.xml'

# Findbugs
findbugs.gradle_task = 'findbugs'
findbugs.report_file = 'app/build/reports/findbugs/findbugs.xml'
findbugs.report

# JUnit tests
message Dir['app/build/test-results/testReleaseUnitTest/*.xml']
#junit.parse_files Dir['app/build/test-results/testReleaseUnitTest/*.xml']
#junit.show_skipped_tests = true
#junit.report

# Linear history
linear_history.validate!

# Textlint
textlint.max_severity = 'warn'
textlint.lint

# Todoist
todoist.message = 'Please fix all TODOs'
todoist.warn_for_todos
todoist.print_todos_table

# LGTM
lgtm.check_lgtm
