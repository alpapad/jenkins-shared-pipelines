def call(Closure body) {
    
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    pipeline {
        agent any
        stages {
            stage("echo parameters") {
                steps {
                    sh "env | sort"
                }
            }
            stage("Prepare Build Environment") {
                steps {
                    echo "Prepare Build Environment ${pipelineParams.email} ${pipelineParams.someVar}"
                }
            }
            stage("Source Code Checkout") {
                steps {
                    echo 'Source Code Checkout'
                    sh "ls"
                }
            }
            stage("Build Application") {
                steps {
                    echo 'build'
                }
            }
            stage("Publish Artifacts") {
                steps {
                    echo 'Publish Artifacts'
                }
            }
            stage("Deploy Application") {
                steps {
                     echo 'Deploy Application'
                }
            }
  
        }
        post {
            always {
              echo 'Post action'
            }
        }
    }
}
