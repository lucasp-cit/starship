import { createTreeWithEmptyWorkspace } from '@nx/devkit/testing';
import { Tree, readProjectConfiguration } from '@nx/devkit';

import { serverlessHandlerGeneratorGenerator } from './generator';
import { ServerlessHandlerGeneratorGeneratorSchema } from './schema';

describe('serverless-handler-generator generator', () => {
  let tree: Tree;
  const options: ServerlessHandlerGeneratorGeneratorSchema = { name: 'test' };

  beforeEach(() => {
    tree = createTreeWithEmptyWorkspace();
  });

  it('should run successfully', async () => {
    await serverlessHandlerGeneratorGenerator(tree, options);
    const config = readProjectConfiguration(tree, 'test');
    expect(config).toBeDefined();
  });
});
