import { sharedPlaywrightConfig } from './uat/playwright/playwright.shared';

export default {
  ...sharedPlaywrightConfig,
  testDir: './uat/playwright',
};
