module.exports = function(grunt) {
         grunt.initConfig({

             less: {
                 development: {
                     options: {
                         paths: ["src/assets/css"]
                     },
                     files: {"src/assets/css/styles.css": "src/assets/less/styles.less"}
                 },
                 production: {
                     options: {
                         paths: ["src/assets/css"],
                         cleancss: true
                     },
                     files: {"src/assets/css/styles.css": "src/assets/less/styles.less"}
                 }
             }
         });
         grunt.loadNpmTasks('grunt-contrib-less');
         grunt.registerTask('default', ['less']);
     };