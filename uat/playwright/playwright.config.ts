import { defineConfig } from '@playwright/test';
import { sharedPlaywrightConfig } from './playwright.shared';

export default defineConfig({
  ...sharedPlaywrightConfig,
  testDir: __dirname,
});
