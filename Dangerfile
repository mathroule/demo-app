# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

if github.pr_title =~ /[A-Z]+-[0-9]+:/
    auto_label.wip=(github.pr_json["number"])
else
    fail("PR has invalid name and does not match pattern 'DEMO-123: PR label'")
end

# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

# Ensure a clean commits history
if git.commits.any? { |c| c.message =~ /^Merge branch/ }
  fail('Please rebase to get rid of the merge commits in this PR')
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
