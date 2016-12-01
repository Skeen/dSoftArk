require "lfs"

-- Recursive lua directory iterator using parallelism (coroutines)
function dirtree(dir)
    assert(dir and dir ~= "", "directory parameter is missing or empty")
    if string.sub(dir, -1) == "/" then
        dir=string.sub(dir, 1, -2)
    end

    local function yieldtree(dir)
        for entry in lfs.dir(dir) do
            if entry ~= "." and entry ~= ".." then
                entry=dir.."/"..entry
                local attr=lfs.attributes(entry)
                coroutine.yield(entry,attr)
                if attr.mode == "directory" then
                    yieldtree(entry)
                end
            end
        end
    end

    return coroutine.wrap(function() yieldtree(dir) end)
end

for filename, attr in dirtree(".") do
    if(attr.mode == "file") then
        -- only do this on lua files
        local found = string.find(filename, ".java")
        if(found ~= nil) then
            os.execute("fromdos -p " .. filename .. " &")
        end
    end
end


