import org.gradle.api.artifacts.transform.InputArtifact
import org.gradle.api.artifacts.transform.TransformAction
import org.gradle.api.artifacts.transform.TransformOutputs
import org.gradle.api.artifacts.transform.TransformParameters
import org.gradle.api.file.FileSystemLocation
import org.gradle.api.provider.Provider
import org.gradle.process.ExecOperations

import javax.inject.Inject

abstract class FileSizeTransform implements TransformAction<TransformParameters.None> {
    @InputArtifact
    abstract Provider<FileSystemLocation> getInputArtifact();

    @Inject
    abstract ExecOperations getExecOperations();

    @Override
    void transform(TransformOutputs outputs) {
        def input = inputArtifact.get().asFile
        def output = outputs.file(input.name + ".txt")

        output.text = String.valueOf(input.length())
    }
}
