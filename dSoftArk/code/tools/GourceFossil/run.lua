-- Pull the HTTP timeline from 127.0.0.1
os.execute("lua fossil_pull_timeline.lua")
-- Process HTTP timeline to midway format
os.execute("lua fossil_timeline_processing.lua")
-- Process midway format to gource custom format
os.execute("lua gource_create_customformat.lua")
-- Reverse the gource custom format
os.execute("lua gource_reverse_customformat.lua")
-- Run gource using our gource custom format
os.execute("lua gource_run.lua")
-- Clean up all our temporary files
os.execute("lua files_cleanup.lua")

