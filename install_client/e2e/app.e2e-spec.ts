import { InstallClient2Page } from './app.po';

describe('install-client2 App', function() {
  let page: InstallClient2Page;

  beforeEach(() => {
    page = new InstallClient2Page();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
