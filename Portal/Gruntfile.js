module.exports = function(grunt){
	grunt.initConfig({
		concat: {
			options: {
				separator: '\n'
			},
      ie: {
        files: {
          'js/dest/ie.js': [
            'js/libs/ie/html5shiv.js',
            'js/libs/ie/es5-shim.min.js',
            'js/libs/ie/respond.min.js'
          ]
        }
      },
      modules: {
        files: {
          'js/dest/app.js': ['js/libs/bootstrap/*.js', 'js/modules/*.js', 'js/init.js']
        }
      }
		},
		uglify: {
			options: {
				mangle: true,
				compress: true,
        sourceMap: true
			},
			target: {
        files: {
          'js/dest/ie.min.js': ['js/dest/ie.js'],
          'js/dest/app.min.js': ['js/dest/app.js']
        }
			}
		},
    sass: {
      dist: {
        options: {
          style: 'compressed',
          noCache: true
        },
        files: [{
          expand: true, // Enable dynamic expansion.
          cwd: 'sass/', // Src matches are relative to this path.
          src: ['**/*.scss', '!**/_*.scss'], // Actual pattern(s) to match. Compile all .scss files except partials
          dest: 'css', // Destination path prefix.
          ext: '.css' // Dest filepaths will have this extension.
        }]
      }
    },
	watch: {
		scripts: {
			files: ['js/**/*.js'],
			tasks: ['concat:modules']
		},
  sass: {
			files: ['sass/**/*.scss'],
			tasks: ['sass']
		}
	},
	clean: {
		target: ['js/dest', 'css']
	}
	});

	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-sass');
	grunt.registerTask('default', ['clean','sass', 'concat:modules', 'concat:ie', 'uglify']);
	grunt.registerTask('min', ['clean','uglify']);
};