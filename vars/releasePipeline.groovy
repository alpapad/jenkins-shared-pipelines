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
                    prepareBuildEnvironment()
                    echo "Prepare Build Environment ${pipelineParams.email}"
                }
            }
            stage("Source Code Checkout") {
                steps {
                    echo 'Source Code Checkout'
                    sh "ls"
                }
            }
            stage("Release") {
                steps {
                    echo 'Release'
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
