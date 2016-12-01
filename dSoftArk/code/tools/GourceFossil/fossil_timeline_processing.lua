-- Open an inputfile
fileinname = "timeline.log"
filein = assert(io.open(fileinname, "r"))

-- Open an outputfile
fileoutname = "parsed.log"
fileout = assert(io.open(fileoutname, "w"))

while(true) do
    local line = filein:read("*line")
    if (line == nil) then
        break
    end
    -- Check for divider (date string)
    local date_string = string.match(line, "<div class=\"divider\">(.*)</div>")
    if(date_string ~= nil) then
        fileout:write("DATE:" .. date_string .. "\n") 
    end
    -- Check for timelineTime
    local time_string = string.match(line, "<td class=\"timelineTime\">(.*)</td>")
    if(time_string ~= nil) then
        fileout:write("TIME:" .. time_string .. "\n") 
    end
    -- Check for user
    local j = string.find(line, "<td class=\"timelineTableCell\"")
    if(j~=nil) then
        -- we found a block
        while(true) do
            local line = filein:read("*line")
            if (line == nil) then
                break
            end
            local td_end = string.find(line, "</td>")
            if (td_end ~= nil) then
                break
            end

            local _, _, user_string = string.match(line, "(user: <a href=\"(.*)\">(.*)</a>(.*))")
            if(user_string ~= nil) then
                fileout:write("USER:" .. user_string .. "\n") 
            end

            -- Check files
            local j = string.find(line, "<ul class=\"filelist\">")
            if(j~=nil) then
                while(true) do
                    local line = filein:read("*line")
                    if (line == nil) then
                        break
                    end
                    local ul_end = string.find(line, "</ul>")
                    if (ul_end ~= nil) then
                        break
                    end

                    --newfile & change
                    local file_string = string.match(line, "<li>(.*)&nbsp;")
                    if(file_string ~= nil) then
                        fileout:write("FILE:" .. file_string .. "\n") 
                    end
                    --deletion
                    local file_string = string.match(line, "<li>(.*)</li>")
                    if(file_string ~= nil) then
                        fileout:write("FILE:" .. file_string .. "\n") 
                    end
                end
            end
        end
    end
end

-- Close the files
io.close(filein)
io.close(fileout)
