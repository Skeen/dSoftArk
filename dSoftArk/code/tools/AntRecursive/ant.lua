require "lfs"

for i=0, 100 do
    local directory = lfs.currentdir();

    for file in lfs.dir(directory) do
        if lfs.attributes(file, "mode") == "file" then 
            if(file == "build.xml") then
                os.execute("ant_program " .. arg[1])
                return
            end
        end
    end
    --print("..")
    lfs.chdir("..");
end
