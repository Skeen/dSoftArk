-- Lines
-- All the lines of the file
local lines = {}

-- Open an inputfile
fileinname = "reverse.log"
filein = assert(io.open(fileinname, "r"))

-- Loop though all the lines of the file
-- And save them to lines
local i = 0
while(true) do
    local line = filein:read("*line")
    if (line == nil) then
        break
    end
    i = i + 1
    lines[i] = line
end

-- Open an outputfile
fileoutname = "gource.log"
fileout = assert(io.open(fileoutname, "w"))

-- Write all the lines read, in reverse order
local x=i
while(true) do
    if(x==0) then
        break
    end
    fileout:write(lines[x] .. "\n")
    x = x - 1
end


