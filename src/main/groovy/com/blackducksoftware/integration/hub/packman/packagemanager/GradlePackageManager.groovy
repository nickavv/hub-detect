package com.blackducksoftware.integration.hub.packman.packagemanager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.blackducksoftware.integration.hub.bdio.simple.model.DependencyNode
import com.blackducksoftware.integration.hub.packman.PackageManagerType
import com.blackducksoftware.integration.hub.packman.packagemanager.gradle.GradleInitScriptPackager

@Component
class GradlePackageManager extends PackageManager {
    @Autowired
    GradleInitScriptPackager gradleInitScriptPackager

    PackageManagerType getPackageManagerType() {
        return PackageManagerType.GRADLE
    }

    boolean isPackageManagerApplicable(String sourcePath) {
        File sourceDirectory = new File(sourcePath)
        if (sourcePath && sourceDirectory.isDirectory()) {
            File buildGradleFile = new File(sourceDirectory, "build.gradle")
            return buildGradleFile.isFile()
        }

        false
    }

    List<DependencyNode> extractDependencyNodes(String sourcePath) {
        DependencyNode rootProjectNode = gradleInitScriptPackager.extractRootProjectNode(sourcePath)
        [rootProjectNode]
    }
}