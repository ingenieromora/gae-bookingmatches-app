module.exports = function(grunt){
    grunt.loadNpmTasks('grunt-wiredep');

    grunt.initConfig({
        wiredep: {
            task: {
                // Point to the files that should be updated when you run `grunt wiredep`
                src: [
                    '../index.html'
                ]
            }
        }
    });
}
