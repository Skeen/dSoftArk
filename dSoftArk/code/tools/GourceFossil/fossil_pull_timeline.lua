require("luacurl")

-- Open a file named timeline
local filename = "timeline.log"
local file = assert(io.open(filename, "wb"))

-- And download the timeline to it, using curl.
local c = assert(curl.new())
assert(c:setopt(curl.OPT_HTTPGET, true))
assert(c:setopt(curl.OPT_NOPROGRESS, false))
assert(c:setopt(curl.OPT_BUFFERSIZE, 5000))
assert(c:setopt(curl.OPT_HTTPHEADER, "Connection: Keep-Alive", "Accept-Language: en-us"))
assert(c:setopt(curl.OPT_URL, "http://127.0.0.1:8080/timeline?n=1000&y=ci&fc"))
assert(c:setopt(curl.OPT_CONNECTTIMEOUT, 15))
assert(c:setopt(curl.OPT_WRITEFUNCTION,
function(stream, buffer) 
    stream:write(buffer)
    return string.len(buffer)
end))
assert(c:setopt(curl.OPT_PROGRESSFUNCTION, function (_, dltotal, dlnow, uptotal, upnow)
	print(dltotal, dlnow, uptotal, upnow);
end))
assert(c:setopt(curl.OPT_WRITEDATA, file))
assert(c:perform())
assert(c:close())

-- Close the file
file:close()
