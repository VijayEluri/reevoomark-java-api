task :build do
  system("mvn clean package install")
end

task :run do
  Dir.chdir("taglib-inttest") do
    system("mvn jetty:run")
  end
end

namespace "test" do
  task :quiet do
    system("mvn -q test")
  end
  task :verbose do
    system("mvn test")
  end
end

task :test => "test:quiet"
task :default => :test
