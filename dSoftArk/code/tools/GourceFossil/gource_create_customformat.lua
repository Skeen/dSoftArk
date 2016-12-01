-- Open an inputfile
fileinname = "parsed.log"
filein = assert(io.open(fileinname, "r"))

-- Open an outputfile
fileoutname = "reverse.log"
fileout = assert(io.open(fileoutname, "w"))

function getTimeStamp(date_string, time_string)
    local year, month, day = string.match(date_string, "(%d*)-(%d*)-(%d*)")
    local hour, min = string.match(time_string, "(%d*):(%d*)")

    -- Time for log.output
    local time_table = {}
    time_table.year = year
    time_table.month = month
    time_table.day = day
    time_table.hour = hour;
    time_table.min = min;
    return os.time(time_table)
end

function string.trim(s)
  return string.match(s, '^%s*(.*%S)' or '')
end

function getTypeStamp(file_string)
    file_string = string.trim(file_string)

    local _, status = string.match(file_string, "(%a*)%s(.*)")
    if(status == nil) then
        return "M"
    elseif(status == "(new file)") then
        return "A"
    elseif(status == "(deleted)") then
        return "D"
    else
        return "M"
    end
end

function getFileStamp(file_string)
    file_string = string.trim(file_string)
    local length = string.len(file_string)
    local type_string = getTypeStamp(file_string)
    if(type_string == "M") then
        return file_string
    elseif(type_string == "A") then
        return string.sub(file_string, 0, length-11)
    elseif(type_string == "D") then
        return string.sub(file_string, 0, length-10)
    end
end

local date
local time
local user
local file

while(true) do
    local line = filein:read("*line")
    if (line == nil) then
        break
    end

    -- Check for date
    local date_string = string.match(line, "DATE:(.*)")
    if(date_string ~= nil) then
        date = date_string
    end
    -- Check for time
    local time_string = string.match(line, "TIME:(.*)")
    if(time_string ~= nil) then
        time = time_string
    end
    -- Check for user
    local user_string = string.match(line, "USER:(.*)")
    if(user_string ~= nil) then
        user = user_string
    end
    -- Check for file
    local file_string = string.match(line, "FILE:(.*)")
    if(file_string ~= nil) then
        file = file_string

        -- Write it out
        local timestamp = getTimeStamp(date, time)
        local typestamp = getTypeStamp(file)
        local filestamp = getFileStamp(file)
        fileout:write(timestamp .. "|" .. user .. "|" .. typestamp .. "|" .. filestamp .. "\n")
    end
end

-- Close the files
io.close(filein)
io.close(fileout)
